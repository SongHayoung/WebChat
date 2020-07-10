CREATE TABLE ChatRooms(
    RoomId VARCHAR(20) NOT NULL,
    RoomName VARCHAR(20) NOT NULL
);

CREATE INDEX indexRoomId ON ChatRooms(RoomId);

CREATE INDEX indexRoomName ON ChatRooms(RoomName);
