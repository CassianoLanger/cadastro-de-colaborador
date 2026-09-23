package com.cadastro.colaborador.swagger;

import com.cadastro.colaborador.entity.Colaborador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Colaborador", description = "Operações de CRUD de colaboradores")
public interface SwaggerColaborador<T extends Colaborador> {

    @Operation(summary = "Busca um colaborador pelo ID",
            description = "Retorna os dados do colaborador correspondente ao ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<T> getColaborador(
            @Parameter(description = "ID do colaborador", example = "1", required = true)
            @PathVariable("id") int id);

    @Operation(summary = "Cria um novo colaborador",
            description = "Cadastra um colaborador e retorna os dados persistidos com o novo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Colaborador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<T> postColaborador(
            @Parameter(description = "Dados do colaborador", required = true)
            @Valid @RequestBody T colaborador);

    @Operation(summary = "Atualiza um colaborador existente",
            description = "Atualiza os dados do colaborador pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colaborador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<T> putColaborador(
            @Parameter(description = "Dados do colaborador", required = true)
            @Valid @RequestBody T colaborador,
            @Parameter(description = "ID do colaborador a ser atualizado", example = "1", required = true)
            @PathVariable("id") int id);

    @Operation(summary = "Remove um colaborador",
            description = "Remove o colaborador informado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Colaborador removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    void deleteColaborador(
            @Parameter(description = "Colaborador a ser removido", required = true)
            @Valid @RequestBody T colaborador);
}