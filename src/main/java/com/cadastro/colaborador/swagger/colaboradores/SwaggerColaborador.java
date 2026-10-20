package com.cadastro.colaborador.swagger.colaboradores;

import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Tag(name = "Colaborador", description = "Operações de CRUD de colaboradores")
public interface SwaggerColaborador{

    @Operation(summary = "Busca um colaborador pelo ID",
            description = "Retorna os dados do colaborador correspondente ao ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<ColaboradorResponse> getColaborador(
            @Parameter(description = "ID do colaborador", example = "1", required = true)
            @PathVariable("id") Long id);

    @Operation(summary = "Cria um novo colaborador",
            description = "Cadastra um colaborador e retorna os dados persistidos com o novo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Colaborador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<ColaboradorResponse> postColaborador(
            @Parameter(description = "Dados do colaborador", required = true)
            @Valid @RequestBody ColaboradorRequest colaborador);

    @Operation(summary = "Atualiza um colaborador existente",
            description = "Atualiza os dados do colaborador pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colaborador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<ColaboradorResponse> putColaborador(
            @Parameter(description = "Dados do colaborador", required = true)
            @Valid @RequestBody ColaboradorRequest colaborador,
            @Parameter(description = "ID do colaborador a ser atualizado", example = "1", required = true)
            @PathVariable("id") Long id);

    @Operation(summary = "Remove um colaborador",
            description = "Remove o colaborador informado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Colaborador removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    void deleteColaborador(
            @Parameter(description = "ID do colaborador a ser removido", example = "1", required = true)
            @PathVariable("id") Long id);


    @Operation(summary = "Busca todos os colaboradores",
            description = "Busca todos os colaboradores disponiveis")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Colaboradores encontrados"),
            @ApiResponse(responseCode = "404", description = "Colaboradores não encontrados")
    })
    ResponseEntity<Map<Long, ColaboradorResponse>> getAll();
}
