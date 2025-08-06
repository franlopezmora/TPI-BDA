"use client";
import { useState } from "react";

export default function PosicionForm({ onSubmit }) {
  const [form, setForm] = useState({ idVehiculo: "", latitud: "", longitud: "" });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(f => ({ ...f, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({
      idVehiculo: Number(form.idVehiculo),
      latitud:  Number(form.latitud),
      longitud: Number(form.longitud),
      fechaHora: new Date().toISOString().slice(0,19)  // o la que necesites
    });
    setForm({ idVehiculo: "", latitud: "", longitud: "" });
  };

  return (
    <form onSubmit={handleSubmit}
      className="mb-8 grid grid-cols-1 md:grid-cols-4 gap-4 items-end bg-gray-800 p-4 rounded-lg shadow"
    >
      <div>
        <label className="block text-sm mb-1">Vehículo ID</label>
        <input
          name="idVehiculo"
          type="number"
          value={form.idVehiculo}
          onChange={handleChange}
          className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
          required
        />
      </div>
      <div>
        <label className="block text-sm mb-1">Latitud</label>
        <input
          name="latitud"
          type="number"
          step="any"
          value={form.latitud}
          onChange={handleChange}
          className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
          required
        />
      </div>
      <div>
        <label className="block text-sm mb-1">Longitud</label>
        <input
          name="longitud"
          type="number"
          step="any"
          value={form.longitud}
          onChange={handleChange}
          className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white w-full"
          required
        />
      </div>
      <button
        type="submit"
        className="col-span-full h-[33.8px] bg-blue-600 hover:bg-blue-700 text-white px-4 py-1 rounded shadow"
      >
        Crear Posición
      </button>
    </form>
  );
}
