# Diferenciación e integración numérica

Este documento presenta una recopilación teórica sobre métodos numéricos utilizados para la estimación de derivadas e integrales definidas, cuando el enfoque analítico no es posible o práctico.

## Diferenciación numérica

### Método de los tres puntos

#### Definición
Permite calcular derivadas aproximadas utilizando una fórmula que emplea tres puntos: hacia adelante, hacia atrás o centrada.

#### Aplicaciones
- Análisis de datos experimentales.
- Resolución de ecuaciones diferenciales.
- Control de procesos industriales.

#### Fórmulas
- Derivada hacia adelante:  
  f′(x) ≈ (−f(x+2h) + 4f(x+h) − 3f(x)) / (2h)
- Derivada hacia atrás:  
  f′(x) ≈ (3f(x) − 4f(x−h) + f(x−2h)) / (2h)
- Derivada centrada:  
  f′(x) ≈ (f(x+h) − f(x−h)) / (2h)

### Método de los cinco puntos

#### Definición
Proporciona una mejor precisión al utilizar una fórmula centrada basada en cinco puntos equidistantes alrededor del valor de interés.

#### Fórmula
f′(x) ≈ (−f(x+2h) + 8f(x+h) − 8f(x−h) + f(x−2h)) / (12h)

#### Aplicaciones
- Estimación de derivadas en gráficos digitales.
- Simulación de sistemas físicos.

## Integración numérica

### Método del trapecio

#### Definición
Aproxima el área bajo la curva mediante la suma de áreas de trapecios entre puntos consecutivos.

#### Fórmula
∫ₐᵇ f(x) dx ≈ h/2 * [f(a) + 2∑f(xᵢ) + f(b)]

#### Aplicaciones
- Estimación de integrales con pocos puntos.
- Análisis financiero y económico.

### Regla de Simpson 1/3

#### Definición
Ajusta parábolas a pares de segmentos para una mejor aproximación.

#### Requiere
Número de segmentos par.

#### Fórmula
∫ₐᵇ f(x) dx ≈ (h/3) * [f(a) + 4∑f(x_impares) + 2∑f(x_pares) + f(b)]

### Regla de Simpson 3/8

#### Definición
Ajusta polinomios cúbicos a tres segmentos consecutivos.

#### Requiere
Número de segmentos múltiplo de 3.

#### Fórmula
∫ₐᵇ f(x) dx ≈ (3h/8) * [f(a) + 3∑f(x_no_múltiplos_3) + 2∑f(x_múltiplos_3) + f(b)]

### Cuadratura gaussiana

#### Definición
Aproxima la integral mediante un número fijo de evaluaciones en puntos óptimos (nodos) con pesos predefinidos, optimizando la precisión.

#### Aplicaciones
- Métodos espectrales.
- Resolución de integrales con funciones difíciles.

## Consideraciones importantes

- La elección de h afecta directamente la precisión y estabilidad.
- Se debe tener cuidado con funciones no continuas o con derivadas no definidas en el intervalo.

## Bibliografía

- Chapra, S. C., & Canale, R. P. (2007). *Métodos numéricos para ingenieros*. McGraw-Hill.
- Conte, S. D., & de Boor, C. (1980). *Análisis numérico*. Addison-Wesley.
