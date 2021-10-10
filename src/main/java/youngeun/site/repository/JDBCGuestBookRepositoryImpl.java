package youngeun.site.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import youngeun.site.domain.Post;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JDBCGuestBookRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    public JDBCGuestBookRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //@Override
    /*
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("guestbook").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_name", post.getWriterName());
        parameters.put("content", post.getContent());
        parameters.put("created_datetime", post.getCreatedDatetime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());

        return post;
    }

    //@Override
    public Optional<Post> findById(Long id) {
        List<Post> result = jdbcTemplate.query("select * from guestbook where id = ?", postRowMapper(), id);
        return result.stream().findAny();
    }

    //@Override
    public List<Post> findByWriterName(String writerName) {
        return jdbcTemplate.query("select * from guestbook where writer_name = ?", postRowMapper(), writerName);
    }

    //@Override
    public List<Post> findByContent(String content) {
        return jdbcTemplate.query("select * from guestbook where content like '%"+content+"%'", postRowMapper());
    }

    //@Override
    public List<Post> findByWriterAndContent(String writerName, String content) {
        return jdbcTemplate.query("select distinct * from guestbook where writer_name = ? and content like '%"+content+"%'", postRowMapper(), writerName);
    }

    //@Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from guestbook", postRowMapper());
    }

    //@Override
    public int size() {
        return jdbcTemplate.queryForObject("select count(*) from guestbook", int.class);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setWriterName(rs.getString("writer_name"));
            post.setContent(rs.getString("content"));
            post.setCreatedDatetime(rs.getTimestamp("created_datetime").toLocalDateTime());
            return post;
        };
    }
     */
}
