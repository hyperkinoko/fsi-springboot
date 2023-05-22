package com.example.milestone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class MilestoneDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  MilestoneDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Milestone> findAll() {
    String query = "SELECT * FROM milestone";

    List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
    return result.stream()
        .map((Map<String, Object> row) -> new Milestone(
            row.get("id").toString(),
            row.get("title").toString(),
            row.get("description").toString(),
            LocalDate.parse(row.get("deadline").toString())))
        .toList();
  }

  public Milestone findById(String id) {
    String query = "SELECT * FROM milestone WHERE id = ?";
    Map<String, Object> result = jdbcTemplate.queryForMap(query, id);
    Milestone milestone = new Milestone();
    milestone.setId(result.get("id").toString());
    milestone.setTitle(result.get("title").toString());
    milestone.setDescription(result.get("description").toString());
    milestone.setDeadline(LocalDate.parse(result.get("deadline").toString()));
    return milestone;
  }

  public int create(AddMilestoneRequest input) {
    Milestone milestone = new Milestone(
        input.getTitle(),
        input.getDescription(),
        LocalDate.parse(input.getDeadline())
    );
    String sql = "INSERT INTO milestone VALUES(?, ?, ?, ?)";
    return jdbcTemplate.update(sql, milestone.getId(), milestone.getTitle(), milestone.getDescription(), milestone.getDeadline());
//    SqlParameterSource param = new BeanPropertySqlParameterSource(milestone);
//    SimpleJdbcInsert insert =
//        new SimpleJdbcInsert(jdbcTemplate)
//            .withTableName("milestone");
//    insert.execute(param);
  }

  public int update(String id, EditMilestoneRequest input) {
    String query = "UPDATE milestone SET title = ?, description = ?, deadline = ? WHERE id = ?";
    return jdbcTemplate.update(query, input.getTitle(), input.getDescription(), input.getDeadline(), id);
  }

  public int delete(String id) {
     return jdbcTemplate.update("DELETE FROM milestone WHERE id = ?", id);
  }
}
