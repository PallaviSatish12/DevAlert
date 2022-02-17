package com.decathlon.devalert.repository;

import com.decathlon.devalert.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public Team findByTeamId(Long teamId);

    public Team findByTeamName(String teamName);
}
