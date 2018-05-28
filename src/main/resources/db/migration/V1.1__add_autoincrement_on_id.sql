create sequence evenement_id_seq
   owned by evenement.id_evenement;

alter table evenement
   alter column id_evenement set default nextval('evenement_id_seq');

commit;