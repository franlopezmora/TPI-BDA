"use client";
import { useState, useEffect } from "react";

export default function PruebaForm({ onSubmit, pruebaEditar }) {
  const [form, setForm] = useState({
    fechaHoraInicio: "",
    fechaHoraFin: "",
    idVehiculo: "",
    idEmpleado: "",
    idInteresado: "",
  });

  useEffect(() => {
    if (pruebaEditar) {
      setForm({
        fechaHoraInicio: pruebaEditar.fechaHoraInicio ?? "",
        fechaHoraFin: pruebaEditar.fechaHoraFin   ?? "",
        idVehiculo:       pruebaEditar.vehiculo?.id    ?? "",
        idEmpleado:       pruebaEditar.empleado?.legajo ?? "",
        idInteresado:     pruebaEditar.interesado?.id   ?? "",
      });
    } else {
      setForm({
        fechaHoraInicio: "",
        fechaHoraFin:    "",
        idVehiculo:      "",
        idEmpleado:      "",
        idInteresado:    "",
      });
    }
  }, [pruebaEditar]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((f) => ({ ...f, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      fechaHoraInicio: form.fechaHoraInicio,
      fechaHoraFin:    form.fechaHoraFin,
      empleado:   { legajo:   Number(form.idEmpleado)   },
      vehiculo:   { id:       Number(form.idVehiculo)   },
      interesado: { id:       Number(form.idInteresado) },
    };

    onSubmit(payload);

    setForm({
      fechaHoraInicio: "",
      fechaHoraFin:    "",
      idVehiculo:      "",
      idEmpleado:      "",
      idInteresado:    "",
    });
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-8 grid grid-cols-1 md:grid-cols-5 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
    >
    <div>
        <label className="block text-sm mb-1">Inicio</label>
        <input
          type="datetime-local"
          name="fechaHoraInicio"
          value={form.fechaHoraInicio}
          onChange={handleChange}
          className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
          required
        />
    </div>
    <div>
    <label className="block text-sm mb-1">Fin</label>
    <input
        type="datetime-local"
        name="fechaHoraFin"
        value={form.fechaHoraFin}
        onChange={handleChange}
        className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
    />
    </div>
    <div>
    <label className="block text-sm mb-1">Vehículo ID</label>
    <input
        type="number"
        name="idVehiculo"
        value={form.idVehiculo}
        onChange={handleChange}
        className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
        required
    />
    </div>
    <div>
    <label className="block text-sm mb-1">Empleado ID</label>
    <input
        type="number"
        name="idEmpleado"
        value={form.idEmpleado}
        onChange={handleChange}
        className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
        required
    />
    </div>
    <div>
    <label className="block text-sm mb-1">Interesado ID</label>
    <input
        type="number"
        name="idInteresado"
        value={form.idInteresado}
        onChange={handleChange}
        className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
        required
    />
    </div>
    <button
    type="submit"
    className="col-span-full h-[33.8px] bg-blue-600 hover:bg-blue-700 text-white px-4 py-1 rounded"
    >
    {pruebaEditar ? "Actualizar" : "Crear"}
    </button>
    </form>
  );
}
