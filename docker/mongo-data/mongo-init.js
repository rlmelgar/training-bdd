db = db.getSiblingDB('imperialcom');

db.createCollection('transmission');

db.transmission.insertMany([
 {
     "spaceship" : {
         "name" : "shuttle tidarium",
         "type" : "LAMBDA-CLASS",
         "navPoint" : "3:21",
         "cargo" : "Parts and technical crew for the forest moon",
         "crew" : NumberInt(3)
     },
     "petitions" : [
         {
             "_id" : ObjectId("65455bab8607503f29b6d84d"),
             "petition" : "deactivate deflector shield",
             "created" : ISODate("2023-11-03T20:44:27.300+0000")
         }
     ],
     "clearanceCode" : "234sdfgsdfg",
     "active" : true,
     "_class" : "com.rlm.training.bdd.infrastructure.mongodb.document.TransmissionDocument"
 }
]);

db.runCommand({
	createIndexes : "transmission",
	indexes : [
    {
      "v" : 2,
      "key" : {
          "spaceship.name" : 1
      },
      "name" : "spaceship_name_1",
      "unique" : true,
      "ns" : "imperialcom.transmission"
    }
  ]
});

