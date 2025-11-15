package com.agrodb.back.service;

import com.agrodb.back.dto.CreateCertificacaoDto;
import com.agrodb.back.dto.CreateProdutorDto;
import com.agrodb.back.dto.UpdateProdutorDto;
import com.agrodb.back.models.Certificacao;
import com.agrodb.back.models.Produtor_rural;
import com.agrodb.back.nosql.repository.LogOperacaoRepository;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.admin.ProdutorAdminRepository;
import com.agrodb.back.repository.reader.CertificacaoReaderRepository;
import com.agrodb.back.repository.reader.ProdutorReaderRepository;
import com.agrodb.back.repository.writer.CertificacaoWriterRepository;
import com.agrodb.back.repository.writer.ProdutorWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.List;

@Service
public class ProdutorService {

    private final ProdutorWriterRepository produtorWriterRepository;
    private final ProdutorReaderRepository produtorReaderRepository;
    private final ProdutorAdminRepository produtorAdminRepository;
    private final CertificacaoReaderRepository certificacaoReaderRepository;
    private final CertificacaoWriterRepository certificacaoWriterRepository;
    private final LogOperacaoService logOperacaoService;

    public ProdutorService(ProdutorWriterRepository produtorWriterRepository, ProdutorReaderRepository produtorReaderRepository, ProdutorAdminRepository produtorAdminRepository, CertificacaoReaderRepository certificacaoReaderRepository, CertificacaoWriterRepository certificacaoWriterRepository, LogOperacaoService logOperacaoRepository) {
        this.produtorWriterRepository = produtorWriterRepository;
        this.produtorReaderRepository = produtorReaderRepository;
        this.produtorAdminRepository = produtorAdminRepository;
        this.certificacaoReaderRepository = certificacaoReaderRepository;
        this.certificacaoWriterRepository = certificacaoWriterRepository;
        this.logOperacaoService = logOperacaoRepository;
    }
//  Create
    public Integer createProdutor(CreateProdutorDto createProdutorDto){
        CreateCertificacaoDto certDto = createProdutorDto.certificacao();

                    Certificacao novo = new Certificacao();
                    novo.setOrgao_emissor(createProdutorDto.certificacao().orgaoEmissor());
                    novo.setValidade(certDto.validade());
                    certificacaoWriterRepository.save(novo);


        Produtor_rural entity = new Produtor_rural();
        entity.setNome(createProdutorDto.nome());
        entity.setCpf_cnpj(createProdutorDto.cpf_cnpj());
        entity.setTel(createProdutorDto.tel());
        entity.setData_cadastro(Calendar.getInstance());
        entity.setCertificacao(novo);

        var entitySaved = produtorWriterRepository.save(entity);

        logOperacaoService.registrar("CREATE", "Produtor", entity.getId_rural().toString());

        return entitySaved.getId_rural();
    }
//  Find By Id
    public Produtor_rural buscarProdutorPeloId(Integer id) {

        return produtorReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produtor com id " + id + "não encontrado."
                ));

    }
//  Update
    public Produtor_rural updateProdutor(Integer id, UpdateProdutorDto dto) {

        Produtor_rural existente = produtorReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produtor com id " + id + "não encontrado."
                ));

        if (dto.nome() != null) {
            existente.setNome(dto.nome());
        }
        if (dto.cpf_cnpj() != null) {
            existente.setCpf_cnpj(dto.cpf_cnpj());
        }
        if (dto.tel() != null) {
            existente.setTel(dto.tel());
        }

        Produtor_rural atualizado = produtorWriterRepository.save(existente);

        logOperacaoService.registrar("UPDATE", "Produtor", id.toString());

        return atualizado;

    }
//  Find All
    public List<Produtor_rural> listarTodos(){
        return produtorReaderRepository.findAll();
    }

//  Delete
    public void deletarPorId(Integer id){
        Produtor_rural deletar = produtorReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produtor com id " + id + "não encontrado."
                ));

        produtorAdminRepository.deleteById(id);

        logOperacaoService.registrar("DELETE", "Produtor", id.toString());
    }
}
