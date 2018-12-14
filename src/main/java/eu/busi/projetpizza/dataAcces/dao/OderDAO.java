package eu.busi.projetpizza.dataAcces.dao;



import eu.busi.projetpizza.dataAcces.entity.OderEntity;
import eu.busi.projetpizza.dataAcces.repository.OrderRepository;
import eu.busi.projetpizza.dataAcces.util.OderConverter;
import eu.busi.projetpizza.model.Oder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class OderDAO {

    private final OrderRepository orderRepository;

    public OderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Oder save(Oder oder) {
        OderEntity oderEntity = OderConverter.oderModelToOderrEntity(oder);
        oderEntity = orderRepository.save(oderEntity);
        return OderConverter.oderEntityToOderModel(oderEntity);
    }

}