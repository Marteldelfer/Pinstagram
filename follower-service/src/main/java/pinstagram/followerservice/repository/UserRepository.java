package pinstagram.followerservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import pinstagram.followerservice.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends Neo4jRepository<User, UUID> {

    @Query("MATCH (u:User {id: $id})-[FOLLOWS]->(f:User) RETURN collect(f) AS follows")
    List<User> findFollows(UUID id);

    @Query("MATCH (u:User {id: $id})<-[FOLLOWS]-(f:User) RETURN collect(f) AS follows")
    List<User> findFollowers(UUID id);

}
