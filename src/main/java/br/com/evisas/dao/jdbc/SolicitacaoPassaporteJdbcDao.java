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

import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.util.QueryUtil;

@Repository
public class SolicitacaoPassaporteJdbcDao implements SolicitacaoPassaporteDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<SolicitacaoPassaporte> SOLICITACAO_PASSAPORTE_ROW_MAPPER = new RowMapper<SolicitacaoPassaporte>() {
		@Override
		public SolicitacaoPassaporte mapRow(ResultSet rs, int rowNum) throws SQLException {
			SolicitacaoPassaporte solicitacaoPassaporte = new SolicitacaoPassaporte();
			solicitacaoPassaporte.setId(rs.getLong("id"));
			solicitacaoPassaporte.setNomeSolicitante(rs.getString("nomeSolicitante"));
			solicitacaoPassaporte.setCpfSolicitante(rs.getString("cpfSolicitante"));
			solicitacaoPassaporte.setRgSolicitante(rs.getString("rgSolicitante"));
			solicitacaoPassaporte.setPrevisaoSaida(rs.getDate("previsaoSaida").toLocalDate());
			solicitacaoPassaporte.setStatus(Status.valueOf(rs.getString("status")));
			solicitacaoPassaporte.setMotivoRecusa(rs.getString("motivoRecusa"));
			solicitacaoPassaporte.setObservacao(rs.getString("observacao"));
			solicitacaoPassaporte.setDataSolicitacao(rs.getTimestamp("dataSolicitacao").toLocalDateTime());
			solicitacaoPassaporte.setIdUsuario(rs.getLong("idUsuario"));

			return solicitacaoPassaporte;
		}
	};
	
	@Override
	public long criar(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = getMapCamposEditaveisUsuario(solicitacaoPassaporte);
		parameters.addValue("idUsuario", solicitacaoPassaporte.getIdUsuario());

		KeyHolder idGerado = new GeneratedKeyHolder();
		
		jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.passaporte.criar"), parameters, idGerado);
		return idGerado.getKey().longValue();
	}

	@Override
	public boolean editar(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = getMapCamposEditaveisUsuario(solicitacaoPassaporte);
		parameters.addValue("id", solicitacaoPassaporte.getId());
		parameters.addValue("idUsuario", solicitacaoPassaporte.getIdUsuario());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.passaporte.editar"), parameters) == 1;
	}

	private MapSqlParameterSource getMapCamposEditaveisUsuario(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("nomeSolicitante", solicitacaoPassaporte.getNomeSolicitante());
		parameters.addValue("cpfSolicitante", solicitacaoPassaporte.getCpfSolicitante());
		parameters.addValue("rgSolicitante", solicitacaoPassaporte.getRgSolicitante());
		parameters.addValue("previsaoSaida", solicitacaoPassaporte.getPrevisaoSaida());
		parameters.addValue("status", solicitacaoPassaporte.getStatus().name());
		parameters.addValue("observacao", solicitacaoPassaporte.getObservacao());
		parameters.addValue("dataSolicitacao", solicitacaoPassaporte.getDataSolicitacao());
		
		return parameters;
	}

	@Override
	public List<SolicitacaoPassaporte> listar() {
		return jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.passaporte.listar"), SOLICITACAO_PASSAPORTE_ROW_MAPPER);
	}

	@Override
	public SolicitacaoPassaporte buscarPorId(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		
		List<SolicitacaoPassaporte> solicitacoes = jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.passaporte.buscar.por.id"), parameters, SOLICITACAO_PASSAPORTE_ROW_MAPPER);
		return solicitacoes.isEmpty() ? null : solicitacoes.get(0);
	}

	@Override
	public List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idUsuario", idUsuario);
		
		return jdbcTemplate.query(QueryUtil.getQueryByName("solicitacao.passaporte.buscar.por.usuario"), parameters, SOLICITACAO_PASSAPORTE_ROW_MAPPER);
	}

	@Override
	public boolean alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoPassaporte.getId());
		parameters.addValue("status", solicitacaoPassaporte.getStatus().name());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.passaporte.alterar.status"), parameters) == 1;
	}

	/**
	 * Faz verificação do id do usuário para constatar se o mesmo é o criador da solicitação, e pode, portanto, alterá-la.
	 */
	@Override
	public boolean alterarStatusVerificaUsuario(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoPassaporte.getId());
		parameters.addValue("status", solicitacaoPassaporte.getStatus().name());
		parameters.addValue("idUsuario", solicitacaoPassaporte.getIdUsuario());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.passaporte.alterar.status.verifica.usuario"), parameters) == 1;
	}

	@Override
	public boolean alterarStatusEMotivoRecusa(SolicitacaoPassaporte solicitacaoPassaporte) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", solicitacaoPassaporte.getId());
		parameters.addValue("status", solicitacaoPassaporte.getStatus().name());
		parameters.addValue("motivoRecusa", solicitacaoPassaporte.getMotivoRecusa());

		return jdbcTemplate.update(QueryUtil.getQueryByName("solicitacao.passaporte.alterar.status.e.motivo.recusa"), parameters) == 1;
	}
}
