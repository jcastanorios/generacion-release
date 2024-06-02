package com.mercadolibre.spring.course.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Messages {
    public static final String ERROR_SAVE_FRUIT="Ocurrió un error almacenando fruta en el kvs";
    public static final String NOT_NULL="El nombre no puede ser nulo";
    public static final String NAME_NOT_VALID="El nombre de la fruta tienen que ser letras no pueden ser números, ni caracteres especiales.";
    public static final String GREATHER_THAN_ZERO="La cantidad debe ser siempre un número mayor a 0.";
    public static final String OWNER_NOT_NULL="El header owner no debe venir vacío.";
    public static final String FLOAT_GREATHER_THAN_ZERO="El precio debe ser un flotante mayor a 0.";

    public static final String FRUIT_NOT_FOUND="No existe una fruta asociada a este identificador";
    public static final String ERROR_SEARCH_FRUIT="Ocurrió un error consultando la información de la fruta";

    public static final String ERROR_UPDATE_FRUIT="Ocurrió un error actualizando la información de la fruta por identificador";
    public static final String ERROR_DELETE_FRUIT="Ocurrió un error eliminando la información de la fruta por identificador";
}
