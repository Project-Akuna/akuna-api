package ph.com.adcpp.models.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.models.entity.User;

import java.util.List;
import java.util.Objects;

@Builder
@Setter
@Getter
public class UserResponseBuilder {

    private Long id;
    private Long pid;
    private String name;

    public static UserResponseBuilder buildResponse(User user) {
        return UserResponseBuilder.builder().id(user.getId())
                .pid(Objects.nonNull(user.getUpline()) ? user.getUpline().getId() : null)
                .name(user.getUsername())
                .build();
    }

    public static void asd(User user, List<UserResponseBuilder> userList) {
        if (Objects.nonNull(user.getDownlines())) {
            user.getDownlines().forEach(downline -> {
                userList.add(buildResponse(downline));
                asd(downline, userList);
            });
        }
    }
}