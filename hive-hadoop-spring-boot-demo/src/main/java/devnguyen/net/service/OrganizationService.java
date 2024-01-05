package devnguyen.net.service;


import devnguyen.net.dto.Organization;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final HiveService hiveService;

    public static Function<ResultSet, Organization> map = (resultSet) -> {

        return null;
    };

    public List<Organization> getAll() {
        return hiveService.queryHive("select * from organizations", new ArrayList<>(), map);
    }

    public Optional<Organization> findById(String id) {
        return hiveService.queryHive("select * from organizations where id = ? ", List.of(id), map).stream().findFirst();
    }
}
