package fi.digitraffic.graphql.rail.links;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import fi.digitraffic.graphql.rail.entities.QTrain;
import fi.digitraffic.graphql.rail.entities.Train;
import fi.digitraffic.graphql.rail.entities.TrainId;
import fi.digitraffic.graphql.rail.links.base.OneToOneLink;
import fi.digitraffic.graphql.rail.model.TrainLocationTO;
import fi.digitraffic.graphql.rail.model.TrainTO;
import fi.digitraffic.graphql.rail.querydsl.AllFields;
import fi.digitraffic.graphql.rail.repositories.TrainIdOptimizer;
import fi.digitraffic.graphql.rail.to.TrainTOConverter;

@Component
public class TrainLocationToTrainLink extends OneToOneLink<TrainId, TrainLocationTO, Train, TrainTO> {
    @Autowired
    private TrainTOConverter trainTOConverter;

    @Override
    public String getTypeName() {
        return "TrainLocation";
    }

    @Override
    public String getFieldName() {
        return "train";
    }

    @Override
    public TrainId createKeyFromParent(final TrainLocationTO trainLocationTO) {
        return new TrainId(trainLocationTO.getTrainNumber(), trainLocationTO.getDepartureDate());
    }

    @Override
    public TrainId createKeyFromChild(final TrainTO trainTO) {
        return new TrainId(trainTO.getTrainNumber(), trainTO.getDepartureDate());
    }

    @Override
    public TrainTO createChildTOFromTuple(final Tuple tuple) {
        return trainTOConverter.convert(tuple);
    }

    @Override
    public Class getEntityClass() {
        return Train.class;
    }

    @Override
    public Expression[] getFields() {
        return AllFields.TRAIN;
    }

    @Override
    public EntityPath getEntityTable() {
        return QTrain.train;
    }

    @Override
    public BooleanExpression createWhere(final List<TrainId> keys) {
        return TrainIdOptimizer.optimize(QTrain.train.id, keys);
    }


}
