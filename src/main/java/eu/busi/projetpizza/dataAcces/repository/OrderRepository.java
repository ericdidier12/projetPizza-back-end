package eu.busi.projetpizza.dataAcces.repository;
import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OderEntity,Long> {}

