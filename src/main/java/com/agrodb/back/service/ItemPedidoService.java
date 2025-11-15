package com.agrodb.back.service;

import com.agrodb.back.dto.AddItemDto;
import com.agrodb.back.models.*;
import com.agrodb.back.repository.reader.*;
import com.agrodb.back.repository.writer.ItemPedidoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemPedidoService {

    public final PedidoReaderRepository pedidoReaderRepository;
    public final ProdutoReaderRepository produtoReaderRepository;
    public final ProdutorReaderRepository produtorReaderRepository;
    public final ProdutorProdutoReaderRepository produtorProdutoReaderRepository;
    public final ItemPedidoWriterRepository itemPedidoWriterRepository;
    public final ItemPedidoReaderRepository itemPedidoReaderRepository;

    public ItemPedidoService(PedidoReaderRepository pedidoReaderRepository, ProdutoReaderRepository produtoReaderRepository, ProdutorReaderRepository produtorReaderRepository, ProdutorProdutoReaderRepository produtorProdutoReaderRepository, ItemPedidoWriterRepository itemPedidoWriterRepository, ItemPedidoReaderRepository itemPedidoReaderRepository) {
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.produtoReaderRepository = produtoReaderRepository;
        this.produtorReaderRepository = produtorReaderRepository;
        this.produtorProdutoReaderRepository = produtorProdutoReaderRepository;
        this.itemPedidoWriterRepository = itemPedidoWriterRepository;
        this.itemPedidoReaderRepository = itemPedidoReaderRepository;
    }

    public ItemPedidoId addItemPedido(Integer idPedido, AddItemDto dto) {

        Pedido pedido = pedidoReaderRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido de id " + idPedido + " não encontrado."
                ));

        Produto produto = produtoReaderRepository.findById(dto.idProduto())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto de id " + dto.idProduto() + " não encontrado."
                ));

        Produtor_rural produtor = produtorReaderRepository.findById(dto.idProdutor())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produtor de id " + dto.idProdutor() + " não encontrado."
                ));

        ProdutorProdutoId relacaoId = new ProdutorProdutoId(
                produtor.getId_rural(),
                produto.getId_produto()
        );

        boolean existeRelacao = produtorProdutoReaderRepository
                .findById(relacaoId)
                .isPresent();

        if (!existeRelacao) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Produtor de id " + produtor.getId_rural() +
                            " não possui o produto de id " + produto.getId_produto() +
                            " cadastrado em produtor_produto."
            );
        }

        ItemPedidoId itemId = new ItemPedidoId(
                pedido.getId_pedido(),
                produto.getId_produto(),
                produtor.getId_rural()
        );

        ItemPedido item = new ItemPedido();
        item.setId(itemId);
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setProdutor(produtor);
        item.setQuantidade(dto.quantidade());
        item.setPreco_unitario(dto.precoUnitario());

        var itemSaved = itemPedidoWriterRepository.save(item);
        return itemSaved.getId();
    }


    public List<ItemPedido> listaItens(Integer idPedido) {
        Pedido pedidoId = pedidoReaderRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido com id " + idPedido + "não encontrado."
                ));


        return itemPedidoReaderRepository.findByPedido(pedidoId);
    }
}
