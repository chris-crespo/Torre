# Torre

## Funcionamiento
El programa está compuesto por tres partes: la vista; el control, que simula
trata de simular una torre de control real; y la base de datos.

Cada vez que se realiza una solicitud, esta se inserta tanto en una 
estructura, *Operations*, explicada debajo, como en la base de datos. Si
es una solicitud de **Aterrizaje** y tiene algún motivo especial, se inserta
en una estructura aparte.

Cuando se realiza una autorización, el programa trata de obtener, primero,
un aterrizaje de emergencia o, en caso de no haberlo, cualquier otra 
operación, siguiendo las reglas establecidas en la documentación.

Se muestran únicamente las autorizaciones realizadas ese mismo día. Por esta
razón, se guardan solo en la base de datos y no en la memoria del programa.

### Operations
*Operations*, está compuesta por tres colas: una principal, una de 
aterrizajes, y una de despegues. Cada vez que se inserta una 
solucitud se compara con el tipo de la última operación en la 
cola principal. Si los tipos coinciden, se inserta en una de 
las otras colas, según le corresponda. Si no, se inserta en 
la principal, seguida de la primera operación de la cola contraria, 
en caso de que existiera.

## Pruebas
Se adjuntan, junto al resto del proyecto, las prueba realizadas para la 
implementación de la cola y de *Operations*.
