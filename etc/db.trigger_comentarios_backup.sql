CREATE TABLE private.comentarios_backup
(
  cid bigserial NOT NULL,
  uid bigint NOT NULL,
  artid bigint NOT NULL,
  contenido character varying NOT NULL,
  fecha timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_comentarios PRIMARY KEY (cid),
  CONSTRAINT fk_comentarios_artid FOREIGN KEY (artid)
      REFERENCES articulos (artid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE FUNCTION trigger_backup_comentarios() RETURNS trigger AS $_$
    BEGIN
      INSERT INTO private.comentarios_backup SELECT OLD.*;
      RETURN OLD;
    END;
$_$ LANGUAGE plpgsql;

CREATE TRIGGER trg_backup_comentarios
  AFTER DELETE ON comentarios
      FOR EACH ROW EXECUTE PROCEDURE trigger_backup_comentarios();
