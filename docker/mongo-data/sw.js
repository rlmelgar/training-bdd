db.starwars.find().sort({_id: 1}).forEach(function (frame) {
  cls();
  const speedX = 1;
  print(frame.line1);
  print(frame.line2);
  print(frame.line3);
  print(frame.line4);
  print(frame.line5);
  print(frame.line6);
  print(frame.line7);
  print(frame.line8);
  print(frame.line9);
  print(frame.line10);
  print(frame.line11);
  print(frame.line12);
  print(frame.line13);
  sleep(frame.sleeptime * 120 * speedX);
});