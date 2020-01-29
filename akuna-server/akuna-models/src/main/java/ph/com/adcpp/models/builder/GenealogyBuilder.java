package ph.com.adcpp.models.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.models.entity.User;

import java.util.List;
import java.util.Objects;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Builder
@Getter
@Setter
public class GenealogyBuilder {

    private Long id;
    private Long pid;
    private String name;
    private String username;

    public static GenealogyBuilder create(User user) {
        return GenealogyBuilder.builder().id(user.getId())
                .pid((Objects.nonNull(user.getUpline()) ? user.getUpline().getId() : null))
                .name(buildFullName(user))
                .username(user.getUsername())
                .build();
    }

    public static void buildGenealogy(User user, List<GenealogyBuilder> userList) {
        if (Objects.nonNull(user.getDownlines())) {
            user.getDownlines().forEach(downline -> {
                userList.add(create(downline));
                buildGenealogy(downline, userList);
            });
        }
    }

    private static String buildFullName(User user) {
        if (Objects.nonNull(user.getFirstName()) && Objects.nonNull(user.getLastName())) {
            return user.getFirstName() + " " + user.getLastName();
        }

        return "";
    }
}
