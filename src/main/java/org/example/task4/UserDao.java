package org.example.task4;

//@Repository
//@RequiredArgsConstructor
public class UserDao {
//    private final JdbcTemplate jdbcTemplate;
//    private final RowMapper<User> userRowMapper;
//
//    public User create(User user) {
//        String sql = "INSERT INTO users (username) VALUES (?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, user.getUsername());
//            return ps;
//        }, keyHolder);
//
//        user.setId((Long) keyHolder.getKeys().get("id"));
//        return user;
//    }
//
//    public Optional<User> findById(Long id) {
//        String sql = "SELECT * FROM users WHERE id = ?";
//        try {
//            User user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
//            return Optional.ofNullable(user);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
//
//    public List<User> findAll() {
//        String sql = "SELECT * FROM users ORDER BY id";
//        return jdbcTemplate.query(sql, userRowMapper);
//    }
//
//    public User update(User user) {
//        String sql = "UPDATE users SET username = ? WHERE id = ?";
//        jdbcTemplate.update(sql, user.getUsername(), user.getId());
//        return user;
//    }
//
//    public boolean delete(Long id) {
//        String sql = "DELETE FROM users WHERE id = ?";
//        int affectedRows = jdbcTemplate.update(sql, id);
//        return affectedRows > 0;
//    }
//
//    public boolean deleteAll() {
//        String sql = "DELETE FROM users";
//        int affectedRows = jdbcTemplate.update(sql);
//        return affectedRows > 0;
//    }
}
