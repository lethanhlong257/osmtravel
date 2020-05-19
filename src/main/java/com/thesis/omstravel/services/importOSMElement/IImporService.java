package com.thesis.omstravel.services.importOSMElement;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Relation;
import com.thesis.omstravel.model.Way;

import java.util.List;

public interface IImporService {
    void importNode(List<NodeT> nodes);
    void importWay(List<Way> listWay);
    void importRelation(List<Relation> relationList);
}
