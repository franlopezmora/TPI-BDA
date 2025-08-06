"use client";
import { useState, useEffect } from "react";

export default function VehiculoForm({ modelos, onSubmit, vehiculoEditar }) {
  const [form, setForm] = useState({
    patente: "",
    anio: "",
    idModelo: "",
  });

  useEffect(() => {
    if (vehiculoEditar) {
      setForm({
        patente: vehiculoEditar.patente || "",
        anio: vehiculoEditar.anio || "",
        idModelo: vehiculoEditar.idModelo.toString() || "",
      });
    } else {
      setForm({ patente: "", anio: "", idModelo: "" });
    }
  }, [vehiculoEditar]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({
      ...form,
      anio: Number(form.anio),
      idModelo: Number(form.idModelo),
    });
    setForm({ patente: "", anio: "", idModelo: "" });
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-8 grid grid-cols-1 md:grid-cols-4 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
    >
      <div>
        <label className="block mb-1 text-sm">Patente</label>
        <input
          name="patente"
          value={form.patente}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <div>
        <label className="block mb-1 text-sm">Año</label>
        <input
          name="anio"
          type="number"
          value={form.anio}
          onChange={handleChange}
          className="w-full bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          required
        />
      </div>
      <div>
        <label className="block mb-1 text-sm">Modelo</label>
          <select
            name="idModelo"
            value={form.idModelo}
            onChange={handleChange}
            className="w-full h-[33.8px] bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white text-sm"
            required
          >
            <option value="">Seleccione un modelo</option>
            {modelos.map((m) => (
                <option key={m.id} value={m.id.toString()}>
                  {m.descripcion}
                </option>
            ))}
          </select>
      </div>
      <button
        type="submit"
        className="bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white px-4 py-1 rounded shadow w-full"
      >
        {vehiculoEditar ? "Actualizar" : "Crear"}
      </button>
    </form>
  );
}
