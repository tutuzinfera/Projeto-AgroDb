package com.agrodb.back.service;

import com.agrodb.back.dto.VincularProdutorProdutoDto;
import com.agrodb.back.models.ProdutorProduto;
import com.agrodb.back.models.ProdutorProdutoId;
import com.agrodb.back.repository.reader.ProdutoReaderRepository;
import com.agrodb.back.repository.reader.ProdutorProdutoReaderRepository;
import com.agrodb.back.repository.reader.ProdutorReaderRepository;
import com.agrodb.back.repository.writer.ProdutorProdutoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;

@Service
public class ProdutorProdutoService {

    public final ProdutorProdutoWriterRepository produtorProdutoWriterRepository;
    public final ProdutorProdutoReaderRepository produtorProdutoReaderRepository;
    public final ProdutorReaderRepository produtorReaderRepository;
    public final ProdutoReaderRepository produtoReaderRepository;
    public ProdutorProdutoService(ProdutorProdutoWriterRepository produtorProdutoWriterRepository, ProdutorProdutoReaderRepository produtorProdutoReaderRepository, ProdutorReaderRepository produtorReaderRepository, ProdutoReaderRepository produtoReaderRepository) {
        this.produtorProdutoWriterRepository = produtorProdutoWriterRepository;
        this.produtorProdutoReaderRepository = produtorProdutoReaderRepository;
        this.produtorReaderRepository = produtorReaderRepository;
        this.produtoReaderRepository = produtoReaderRepository;
    }

    public ProdutorProdutoId vincularProdutoAoProdutor(Integer idProdutor,
                                                       VincularProdutorProdutoDto dto) {

        var produtor = produtorReaderRepository.findById(idProdutor)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produtor com id " + idProdutor + " não encontrado"
                ));

        var produto = produtoReaderRepository.findById(dto.idProduto())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto com id " + dto.idProduto() + " não encontrado"
                ));

        ProdutorProdutoId id = new ProdutorProdutoId(idProdutor, dto.idProduto());

        ProdutorProduto relacao = new ProdutorProduto();
        relacao.setId(id);
        relacao.setFk_id_produtor(produtor);
        relacao.setFk_id_produto(produto);
        relacao.setQuantidade(dto.quantidadeDisponivel());

        ProdutorProduto salvo = produtorProdutoWriterRepository.save(relacao);

        // o ID da entidade salva é o próprio ProdutorProdutoId
        return salvo.getId();
    }
}

