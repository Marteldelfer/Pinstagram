package pinstagram.followerservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Node("User")
public class User {

    @Id
    private UUID id;

    @Version
    private Long version;

    @Relationship(type = "FOLLOWS")
    private Set<User> follows;

    private Instant createdAt;

    public User(UUID id) {
        this.id = id;
        this.follows = new HashSet<>();
        this.createdAt = Instant.now();
    }
}
