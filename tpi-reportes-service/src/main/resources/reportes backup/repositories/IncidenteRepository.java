package com.tpi.reportes.repositories;

import com.tpi.reportes.entities.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {

    @Query("SELECT i FROM Incidente i  JOIN FETCH i.tipoIncidente ti JOIN FETCH i.incidenteId id WHERE" +
            " ti.nombre = :nombreTipo")
    List<Incidente> findIncidenteByTipoIncidente(@Param("nombreTipo") String nombreTipo);

   // @Query("SELECT i FROM Incidente i JOIN FETCH i.tipoIncidente JOIN FETCH i.prueba p JOIN FETCH p.empleado e" +
    //        " WHERE e.legajo = :legajo")
   // List<Incidente> findIncidenteByEmpleado(@Param("legajo")Long legajo);
    @Query("SELECT i FROM Incidente i WHERE i.incidenteId.idPrueba = :idPrueba")
    List<Incidente> findIncidenteByIdPrueba(@Param("idPrueba") Long idPrueba);

    List<Incidente> findAll();

}