package com.api.codificacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "Anexos")
@Data
public class Anexo {

	@Id
	private String id;

	private String username;//usuario

	private String sa_codetipoanexo;// code tipo anexo
	private String sa_tipoanexo;// tipo anexo
	private String sa_anexo;// anexo
	private String sa_po;// po
	private String uargnsmod;// modelo

	private String itemcode;// codigo SAP
	private String itemname;// name codigo SAP
	// private String sa_foto;//en otra collection

	private String suppliercatalogno;// ref proveedor (suppCatNum)
	private String cardcode;// code proveedor
	private String sa_namecardcode;// name proveedor

	private String defaultwarehouse;// bodega

	private String u_argns_col;// code color
	private String sa_color;// name color
	private String u_argns_scl;// code escala
	private String u_argns_size;// code tallas

	private String u_argns_coo;// pais
	private String u_argns_year;// anio
	private String u_argns_div;// code genero
	private String sa_namegenero;// name genero

	private String u_argns_brand;// code MarcaExterna
	private String sa_namemarcaext;// name marcaExterna
	private String sa_codmarcacodi;// code marca codi
	private String sa_namemarcacodi;// name marca codi

	private String u_argns_int_compii;// composicion

	private String u_argns_int_cuerpo;// code cuerpo
	private String sa_namecuerpo;// name cuerpo
	private String u_argns_int_proced;// code procedencia
	private String sa_procedencia;// name procedencia NO
	private String u_cxs_sldc;// descuento
	private String u_argns_int_tipo_b_m;// code tipo BM
	private String sa_nametipo_b_m;// name tipo BM

	private String u_argns_int_estilo;// code estilo
	private String sa_nameestilo;// name estilo
	private String u_argns_int_flia;// code familia
	private String sa_namefamilia;// name familia
	private String u_argns_int_sflia;// code subfamilia
	private String sa_namesubfamilia;// code subfamilia
	private String u_argns_int_tipo_mod;// code tipo modelo
	private String sa_nametipomodelo;// name tipo modelo
	private String itmsgrpcod;// code grupo
	private String sa_nameitmsgrpcod;// name grupo

	private String u_argns_season;
	private String sa_temporada;
	private String barcode;

	// cantidades y precios
	private Double sa_preciouni;// costo
	private Integer onhand;// cantidad
	private Double sa_pvpconiva;// precio con iva
	private Double price;// precio sin iva
	private Double sa_ppmxmayor;// precio por mayor
	private Integer sa_porcentaje;

	// CAMPOS POR DEFAULT
	private String assetitem;// "Si el ítem es Activo Fijo= tYES Si el ítem NO es Activo Fijo= tNO
	private String manageserialnumbers;// Si el artículo va a manejar SERIES poner tYES, caso contrario, tNO
	private String managebatchnumbers;// Si el artículo va a manejar LOTES poner tYES, caso contrario, tNO
	private String foreignname;// CODIGO ANTERIOR
	private String vatliable;// "Item sujeto o excento a impuesto IVA Si esta sujeto a Impuesto= tYES No está
								// sujeto=tNO
	private String purchaseitem;// Indica si es un Item que se va COMPRAR, si es si poner tYES; caso contrario
								// tNO
	private String salesitem;// Indica si es un Item que se va VENDER, si es si poner tYES; caso contrario
								// tNO
	private String inventoryitem;// Indica si es un Item que va ser INVENTARIADO, si es si poner tYES; caso
									// contrario tNO
	private String glmethod;// "MÉTODO CONTABILIZACION DEL ITEM, PUESER:Por Bodega= glm_WH Por Grupo
							// Articulos= glm_ItemClass Por Item= glm_ItemLevel
	private String managestockbywarehouse;// Si desea llevar un control del Inventario por Almacén poner tYES. Si desea
											// llevar un control STOCK a nivel TODOS LOS ALMACENES poner tNO.
	private String wtliable;// Indica si el Item esta sujeto a Retención IMPUESTOS. En este caso, asignar
							// tYES, caso contrario tNO.
	private String costaccountingmethod;// Método costeo
	private String planningsystem;// Metodo Planificación
	private String procurementmethod;// Método Abastecimiento
	private Integer orderintervals;// Intervalo Pedido
	private Double ordermultiple;// Pedido múltiple
	private String leadtime;// Tiempo Entrega
	private Double minorderquantity;// Cantidad Mínima Compras
	private String itemtype;// * Clase Artículo
	private String uomgroupentry;// * GRUPO IDADES MEDIDA, / PONER EL CODIGO CORRESPONDIENTE LA TABLA ADJUNTA
	// private String sa_codunimedsap;
	// private String sa_unimedsap;
	private String inventoryuomentry;// * UNIDAD INVENTARIO / PONER EL CODIGO CORRESPONDIENTE LA TABLA ADJUNTA
	private String defaultsalesuomentry;// * UNIDAD VENTAS / PONER EL CODIGO CORRESPONDIENTE LA TABLA ADJUNTA
	private String defaultpurchasinguomentry;// * UNIDAD COMPRA / PONER EL CODIGO CORRESPONDIENTE LA TABLA ADJUNTA
	private String u_tipo_bien;// * Si el ítem es BIEN, poner "B", si es SERIVICIO poner "S" y si es ACTIVO
								// poner "A"
	private String u_argns_sizevo;// Posición talla en la escala
	private String u_argns_var;// Dimension
	private String u_argns_appgrp;// Código Segmentación
	private String u_argns_m_group;// * SubFamilia
	private String u_argns_m_type;// * Accesorio o Tela
	private String u_argns_linecode;// * Familia
	private String u_argns_int_dibujo;// * Dibujo
	private String u_argns_int_cuello;// * Cuello
	private String u_argns_int_puno;// * Puño
	private String u_argns_int_perso;// * Tendencia / Personalidad / Categoría
	private String u_cxs_reit;// *

	// enviar a SAP
	private String sa_enviadosap;
}
