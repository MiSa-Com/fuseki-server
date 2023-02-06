package org.server.run;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.graph.Graph;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.core.DatasetGraph;

public class Main {
    public static void main(String[] args) {
        Dataset dataset = RDFDataMgr.loadDataset("files/example_graph.ttl");
        Model schema = RDFDataMgr.loadModel("files/vocabulary.ttl");
        Model model = RDFDataMgr.loadModel("files/example_graph.ttl");
        InfModel infmodel = ModelFactory.createRDFSModel(schema, model);

        Resource minh = infmodel.getResource("https://tourism-vietnam.com/hotelOwner#Minh_Ngo");
        Property age = infmodel.getProperty("https://tourism-vietnam.com/property#age");
        System.out.println(minh.getProperty(age));
        System.out.println(minh.getNameSpace());

        FusekiServer server = FusekiServer.create()
                .port(3332)
                .add("/ds", dataset)
                .build() ;
        server.start() ;
    }
}