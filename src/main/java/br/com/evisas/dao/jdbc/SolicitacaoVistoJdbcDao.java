package br.com.evisas.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.util.QueryUtil;

@Repository
public class SolicitacaoVistoJdbcDao implements SolicitacaoVistoDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<SolicitacaoVisto> SOLICITACAO_VISTO_ROW_MAPPER = new RowMapper<SolicitacaoVisto>() {
		@Override
		public SolicitacaoVisto mapRow(ResultSet rs, int rowNum) throws SQLException {
			SolicitacaoVisto solicitacaoVisto = new SolicitacaoVisto();
			solicitacaoVisto.setId(rs.getLong("id"));
			solicitacaoVisto.setNomeSolicitante(rs.getString("nomeSolicitante"));
			solicitacaoVisto.setCpfSolicitante(rs.getString("cpfSolicitante"));
			solicitacaoVisto.setPaisDeResidencia(rs.getString("paisResidencia"));
			solicitacaoVisto.setPaisAVisitar(rs.getString("paisAVisitar"));
			solicitacaoVisto.setPossuiPassaporte(rs.getBoolean("possuiPassaporte"));
			solicitacaoVisto.setDataNascimentoSolicitante(rs.getDate("dataNascimentoSolicitante").toLocalDate());
			solicitacaoVisto.setStatus(Status.valueOf(rs.getString("status")));
			solicitacaoVisto.setMotivoRecusa(rs.getString("motivoRecusa"));
			solicitacaoVisto.setObservacao(rs.getString("observacao"));
			solicitacaoVisto.setDataSolicitacao(rs.getTimestamp("dataSolicitacao").toLocalDateTime());
			solicitacaoVisto.setIdUsuario(rs.getLong("idUsuario"));

			return solicitacaoVisto;
		}
	};

	@Override
	public long criar(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = getMapCamposEditaveisUsuario(solicitacaoVisto);
		parameters.addValue("idUsuario", solicitacaoVisto.getIdUsuario());

		KeyHolder idGerado = new GeneratedKeyHolder();
		
		jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.visto.criar"), parameters, idGerado);
		return idGerado.getKey().longValue();
	}

	@Override
	public boolean editar(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = getMapCamposEditaveisUsuario(solicitacaoVisto);
		parameters.addValue("id", solicitacaoVisto.getId());
		parameters.addValue("idUsuario", solicitacaoVisto.getIdUsuario());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.visto.editar"), parameters) == 1;
	}

	private MapSqlParameterSource getMapCamposEditaveisUsuario(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("nomeSolicitante", solicitacaoVisto.getNomeSolicitante());
		parameters.addValue("cpfSolicitante", solicitacaoVisto.getCpfSolicitante());
		parameters.addValue("paisResidencia", solicitacaoVisto.getPaisDeResidencia());
		parameters.addValue("paisAVisitar", solicitacaoVisto.getPaisAVisitar());
		parameters.addValue("possuiPassaporte", solicitacaoVisto.getPossuiPassaporte());
		parameters.addValue("dataNascimentoSolicitante", solicitacaoVisto.getDataNascimentoSolicitante());
		parameters.addValue("status", solicitacaoVisto.getStatus().name());
		parameters.addValue("observacao", solicitacaoVisto.getObservacao());
		parameters.addValue("dataSolicitacao", solicitacaoVisto.getDataSolicitacao());
		
		return parameters;
	}

	@Override
	public List<SolicitacaoVisto> listar() {
		return jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.visto.listar"), SOLICITACAO_VISTO_ROW_MAPPER);
	}

	@Override
	public SolicitacaoVisto buscarPorId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		
		List<SolicitacaoVisto> solicitacoes = jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.visto.buscar.por.id"), parameters, SOLICITACAO_VISTO_ROW_MAPPER);
		return solicitacoes.isEmpty() ? null : solicitacoes.get(0);
	}

	@Override
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idUsuario", idUsuario);
		
		return jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.visto.buscar.por.usuario"), parameters, SOLICITACAO_VISTO_ROW_MAPPER);
	}

	@Override
	public boolean alterarStatus(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoVisto.getId());
		parameters.addValue("status", solicitacaoVisto.getStatus().name());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.visto.alterar.status"), parameters) == 1;
	}

	/**
	 * Faz verificação do id do usuário para constatar se o mesmo é o criador da solicitação, e pode, portanto, alterá-la.
	 */
	@Override
	public boolean alterarStatusVerificaUsuario(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoVisto.getId());
		parameters.addValue("status", solicitacaoVisto.getStatus().name());
		parameters.addValue("idUsuario", solicitacaoVisto.getIdUsuario());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.visto.alterar.status.verifica.usuario"), parameters) == 1;
	}

	@Override
	public boolean alterarStatusEMotivoRecusa(SolicitacaoVisto solicitacaoVisto) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoVisto.getId());
		parameters.addValue("status", solicitacaoVisto.getStatus().name());
		parameters.addValue("motivoRecusa", solicitacaoVisto.getMotivoRecusa());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.visto.alterar.status.e.motivo.recusa"), parameters) == 1;
	}
}
