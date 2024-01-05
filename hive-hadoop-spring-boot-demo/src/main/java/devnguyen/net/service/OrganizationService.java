package devnguyen.net.service;


import devnguyen.net.dto.Organization;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrganizationService {
    private final HiveService hiveService;

    public static Function<ResultSet, Organization> map = (resultSet) -> {
        var item = new Organization();
        try {
            item.setId(resultSet.getString("id"));
            item.setName(resultSet.getString("name"));
            item.setName(resultSet.getString("website"));
            item.setCountry(resultSet.getString("country"));
            item.setDescription(resultSet.getString("description"));
            item.setFounded(resultSet.getString("founded"));
            item.setIndustry(resultSet.getString("industry"));
            item.setNumberOfEmployees(resultSet.getInt("number_of_employees"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return item;
    };

    public List<Organization> getAll() {
        return hiveService.queryHive("select * from organizations", new ArrayList<>(), map);
    }

    public Optional<Organization> findById(String id) {
        return hiveService.queryHive("select * from organizations where id = ? ", List.of(id), map).stream().findFirst();
    }
}
