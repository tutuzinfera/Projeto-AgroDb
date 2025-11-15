package com.agrodb.back.service;

import com.agrodb.back.dto.ClienteResponseDto;
import com.agrodb.back.dto.CreateClienteDto;
import com.agrodb.back.dto.CreateEnderecoDto;
import com.agrodb.back.dto.UpdateClienteDto;
import com.agrodb.back.models.Cliente;
import com.agrodb.back.models.Endereco;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.admin.ClienteAdminRepository;
import com.agrodb.back.repository.reader.ClienteReaderRepository;
import com.agrodb.back.repository.reader.EnderecoReaderRepository;
import com.agrodb.back.repository.writer.ClienteWriterRepository;
import com.agrodb.back.repository.writer.EnderecoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteWriterRepository clienteWriterRepository;
    private final ClienteReaderRepository clienteReaderRepository;
    private final ClienteAdminRepository clienteAdminRepository;
    private final EnderecoReaderRepository enderecoReaderRepository;
    private final EnderecoWriterRepository enderecoWriterRepository;
    private final LogOperacaoService logOperacaoService;



    public ClienteService(ClienteWriterRepository clienteWriterRepository, ClienteReaderRepository clienteReaderRepository, ClienteAdminRepository clienteAdminRepository, EnderecoReaderRepository enderecoReaderRepository, EnderecoWriterRepository enderecoWriterRepository, LogOperacaoService logOperacaoService) {
        this.clienteWriterRepository = clienteWriterRepository;
        this.clienteReaderRepository = clienteReaderRepository;
        this.clienteAdminRepository = clienteAdminRepository;
        this.enderecoReaderRepository = enderecoReaderRepository;
        this.enderecoWriterRepository = enderecoWriterRepository;
        this.logOperacaoService = logOperacaoService;
    }

    //Criar um cliente
    public Integer createCliente(CreateClienteDto createClienteDto) {

//      Normaliza o CEP (tira hífen, espaço, etc)
        CreateEnderecoDto endDto = createClienteDto.endereco();
        String cepLimpo = endDto.cep().replaceAll("\\D", "");

        Endereco endereco = enderecoReaderRepository.findById(cepLimpo)
                .orElseGet(() -> {
                    Endereco novo = new Endereco();
                    novo.setCep(cepLimpo);
                    return enderecoWriterRepository.save(novo);
                });


        Cliente entity = new Cliente();
        entity.setNome(createClienteDto.nome());
        entity.setCpf(createClienteDto.cpf());
        entity.setTel(createClienteDto.tel());
        entity.setCep(endereco);

        var clienteSaved = clienteWriterRepository.save(entity);

        logOperacaoService.registrar("CREATE", "Cliente", entity.getIdCliente().toString());

        return clienteSaved.getIdCliente();

    }

    //Buscar Cliente By Id
    public Cliente buscarClientePeloId(Integer id) {
        return clienteReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com id " + id + " não encontrado.")
                );
    }


    //Atualizar completa ou Parcialmente um Cliente
    public Cliente updateCliente(Integer id, UpdateClienteDto dto) {

        Cliente existente = clienteReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com id " + id + "não encontrado."
                ));

        if (dto.nome() != null) {
            existente.setNome(dto.nome());
        }
        if (dto.cpf() != null) {
            existente.setCpf(dto.cpf());
        }
        if (dto.tel() != null) {
            existente.setTel(dto.tel());
        }
        if (dto.cep() != null) {
            var endereco = enderecoReaderRepository.findById(dto.cep())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "CEP " + dto.cep() + " não encontrado"
                    ));
            existente.setCep(endereco);
        }
        Cliente atualizado = clienteWriterRepository.save(existente);

        logOperacaoService.registrar("DELETE", "Cliente", id.toString());

        return atualizado;
    }

    //Listar todos os Clientes
    public List<Cliente> listarTodos() {
        return clienteReaderRepository.findAll();
    }

    //Delete By ID
    public void delete(Integer id) {
        boolean existe = clienteWriterRepository.existsById(id);
        if (!existe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente com id " + id + " não encontrado."
            );
        }
        clienteAdminRepository.deleteById(id);

        logOperacaoService.registrar("DELETE", "Cliente", id.toString());
    }
}