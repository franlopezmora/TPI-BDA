"use client";
import { useState, useEffect } from "react";

const TIPOS_DOC = [
  { value: "DNI", label: "DNI" },
  { value: "LC",  label: "LC"  },
  { value: "LE",  label: "LE"  },
  // añade más si necesitas…
];

export default function InteresadoForm({
                                         onSubmit,
                                         interesadoEditar = null,
                                       }) {
  const [form, setForm] = useState({
    tipoDocumento: "DNI",
    documento: "",
    nombre: "",
    apellido: "",
    nroLicencia: "",
    fechaVencimientoLicencia: "",
    restringido: false,
  });

  useEffect(() => {
    if (interesadoEditar) {
      setForm({
        tipoDocumento: interesadoEditar.tipoDocumento ?? "DNI",
        documento: interesadoEditar.documento ?? "",
        nombre: interesadoEditar.nombre ?? "",
        apellido: interesadoEditar.apellido ?? "",
        nroLicencia: interesadoEditar.nroLicencia ?? "",
        fechaVencimientoLicencia:
            interesadoEditar.fechaVencimientoLicencia ?? "",
        restringido: interesadoEditar.restringido ?? false,
      });
    }
  }, [interesadoEditar]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((f) => ({
      ...f,
      [name]:
          type === "checkbox"
              ? checked
              : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // valida aquí si quieres…
    onSubmit(form);
    // limpiar sólo si es creación
    if (!interesadoEditar) {
      setForm({
        tipoDocumento: "DNI",
        documento: "",
        nombre: "",
        apellido: "",
        nroLicencia: "",
        fechaVencimientoLicencia: "",
        restringido: false,
      });
    }
  };

  return (
      <form
          onSubmit={handleSubmit}
          className="mt-10 grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4 p-6 bg-gray-800 rounded-lg shadow"
      >
        {/* Tipo Documento */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Tipo Documento</label>
          <select
              name="tipoDocumento"
              value={form.tipoDocumento}
              onChange={handleChange}
              className="bg-gray-700 border h-[33.8px]  border-gray-600 rounded px-2 py-1 text-white"
          >
            {TIPOS_DOC.map(({ value, label }) => (
                <option key={value} value={value}>
                  {label}
                </option>
            ))}
          </select>
        </div>

        {/* Documento */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Documento</label>
          <input
              name="documento"
              type="text"
              value={form.documento}
              onChange={handleChange}
              required
              className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          />
        </div>

        {/* Nombre */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Nombre</label>
          <input
              name="nombre"
              type="text"
              value={form.nombre}
              onChange={handleChange}
              required
              className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          />
        </div>

        {/* Apellido */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Apellido</label>
          <input
              name="apellido"
              type="text"
              value={form.apellido}
              onChange={handleChange}
              required
              className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          />
        </div>

        {/* Nro. Licencia */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Nro. Licencia</label>
          <input
              name="nroLicencia"
              type="number"
              min="0"
              value={form.nroLicencia}
              onChange={handleChange}
              className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          />
        </div>

        {/* Fecha Vencimiento */}
        <div className="flex flex-col">
          <label className="text-sm mb-1">Vencimiento Licencia</label>
          <input
              name="fechaVencimientoLicencia"
              type="date"
              value={form.fechaVencimientoLicencia}
              onChange={handleChange}
              className="bg-gray-700 border border-gray-600 rounded px-2 py-1 text-white"
          />
        </div>

        {/* Restringido */}
        <div className="flex flex-col items-center justify-center justify-self-center">
          <label htmlFor="restringido" className="text-sm mb-1">
            Restringido
          </label>
          <input
            id="restringido"
            name="restringido"
            type="checkbox"
            checked={form.restringido}
            onChange={handleChange}
            className="h-[34.2px] w-[34.2px] bg-gray-700 border border-gray-600 rounded text-blue-500"
          />
        </div>

        {/* Botón */}
        <div className="flex flex-col self-end">
          <button
              type="submit"
              className="w-full bg-blue-600 h-[33.8px] hover:bg-blue-700 text-white font-semibold py-1 rounded shadow"
          >
            {interesadoEditar ? "Actualizar Interesado" : "Crear Interesado"}
          </button>
        </div>
      </form>
  );
}
