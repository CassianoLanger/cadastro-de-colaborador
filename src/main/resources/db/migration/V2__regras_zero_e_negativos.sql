-- RN001, RN004, RN013 (BUG009): zero é permitido, negativo não.
-- Confira os nomes dos checks da V1 com \d colaborador antes de aplicar.

ALTER TABLE colaborador DROP CONSTRAINT IF EXISTS colaborador_salario_check;
ALTER TABLE colaborador ADD CONSTRAINT colaborador_salario_check CHECK (salario >= 0);

ALTER TABLE colaborador_comissionado DROP CONSTRAINT IF EXISTS colaborador_comissionado_valor_das_vendas_check;
ALTER TABLE colaborador_comissionado ADD CONSTRAINT colaborador_comissionado_valor_das_vendas_check CHECK (valor_das_vendas >= 0);

ALTER TABLE colaborador_comissionado DROP CONSTRAINT IF EXISTS colaborador_comissionado_percentual_de_comissao_check;
ALTER TABLE colaborador_comissionado ADD CONSTRAINT colaborador_comissionado_percentual_de_comissao_check CHECK (percentual_de_comissao >= 0);

-- NULL continua permitido até a decisão sobre RN014
ALTER TABLE colaborador_producao ADD CONSTRAINT colaborador_producao_quantidade_check CHECK (quantidade_produzida IS NULL OR quantidade_produzida >= 0);
ALTER TABLE colaborador_producao ADD CONSTRAINT colaborador_producao_valor_unidade_check CHECK (valor_unidade IS NULL OR valor_unidade >= 0);
