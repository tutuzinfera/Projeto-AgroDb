package com.agrodb.back.service;

import com.agrodb.back.dto.CreateProdutoDto;
import com.agrodb.back.dto.UpdateProdutoDto;
import com.agrodb.back.models.Produto;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.admin.ProdutoAdminRepository;
import com.agrodb.back.repository.reader.ProdutoReaderRepository;
import com.agrodb.back.repository.writer.ProdutoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoWriterRepository produtoWriterRepository;
    private final ProdutoReaderRepository produtoReaderRepository;
    private final ProdutoAdminRepository produtoAdminRepository;
    private final LogOperacaoService logOperacaoService;

    public ProdutoService(ProdutoWriterRepository produtoWriterRepository, ProdutoReaderRepository produtoReaderRepository, ProdutoAdminRepository produtoAdminRepository, LogOperacaoService logOperacaoService) {
        this.produtoWriterRepository = produtoWriterRepository;
        this.produtoReaderRepository = produtoReaderRepository;
        this.produtoAdminRepository = produtoAdminRepository;
        this.logOperacaoService = logOperacaoService;
    }

//  Create
    public Integer createProduto(CreateProdutoDto createProdutoDto){
        Produto entity = new Produto();
        entity.setNome(createProdutoDto.nome());
        entity.setCategoria(createProdutoDto.categoria());
        entity.setPreco_unitario(BigDecimal.valueOf(createProdutoDto.preco_unitario()));
        entity.setUnidade_medida(createProdutoDto.unidade_medida());

        var entitySaved = produtoWriterRepository.save(entity);

        logOperacaoService.registrar("CREATE", "Produto", entity.getId_produto().toString());

        return entitySaved.getId_produto();
    }

//  Find By Id
    public Produto buscarProdutoPeloId(Integer id){
        return produtoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto com id" + id + "não encontrado."
                ));
    }

//  Update
    public Produto updateProduto(Integer id, UpdateProdutoDto dto) {

        Produto existente = produtoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto com id " + id + "não encontrado."
                ));

        if(dto.nome() != null){existente.setNome(dto.nome());}
        if(dto.categoria() != null){existente.setCategoria(dto.categoria());}
        if(dto.preco_unitario() != null){existente.setPreco_unitario(dto.preco_unitario());}
        if(dto.unidade_medida() != null){existente.setUnidade_medida(dto.unidade_medida());}

        Produto atualizado = produtoWriterRepository.save(existente);

        logOperacaoService.registrar("UPDATE", "Produto", id.toString());

        return atualizado;
    }

//  Delete
    public void deletarPorId(Integer id){
        Produto deletar = produtoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto com id " + id + "não encontrado."
                ));

        produtoAdminRepository.deleteById(id);

        logOperacaoService.registrar("DELETE", "Produto", id.toString());
    }

//  Find All
    public List<Produto> listarTodos() {

    return produtoReaderRepository.findAll();
    }
}
