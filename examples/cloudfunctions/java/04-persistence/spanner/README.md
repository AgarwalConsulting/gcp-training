# Spanner

Create tables as below:

```sql
CREATE TABLE Singers (
  SingerId   INT64 NOT NULL,
  FirstName  STRING(1024),
  LastName   STRING(1024),
  SingerInfo BYTES(MAX),
) PRIMARY KEY (SingerId);

CREATE TABLE Albums (
  SingerId     INT64 NOT NULL,
  AlbumId      INT64 NOT NULL,
  AlbumTitle   STRING(MAX),
) PRIMARY KEY (SingerId, AlbumId);
```

Insert data:

```sql
INSERT INTO
  Singers (SingerId,
    FirstName,
    LastName,
    SingerInfo)
VALUES
  (1, -- type: INT64
    'Marc', -- type: STRING(1024)
    'Richards', -- type: STRING(1024)
    NULL -- type: BYTES(MAX)
  );
```
