# Projeto - Grafo Alta Dimensionalidade.

Esse foi um projeto da disciplina de grafos ministrada pelo Prof° Fabricio Enembreck cujo requisitava elaborar a estrutura de um grafo tal que poderia ser aplicado 
para grafos do mundo real.

Dentro do código esta a estrutura, aplicação de algoritmos e de funcionalidades descritas seguintes:
- Estrutura de grafo que pode representar tanto grafo direcionado ou não direcionado.
- Algoritmo de Dijkstra (Melhor caminho).
- Centralidade de proximidade.
- Gerador de grafos aleatórios considerando o n° de vértices, arestas e se deve ser conexo ou não.
- Algoritmo de Prim (Árvore geradora mínima | MST).
- Padrão Pajek para leitura e exportação de grafos salvando em ".txt".
- Verificação de conexo.
- Imprime adjacências.
- Busca de profundidade (DFS).
- Imprime Componentes.
- Verificação se possui caminho ou circuito euleriano.

Fora a base o projeto consistia em usar um DataSet e modelar um grafo, utilizei o dataset providenciado no kaggle.com no seguinte link https://www.kaggle.com/datasets/maharshipandya/-spotify-tracks-dataset
todos os créditos à MAHARSHIPANDYA pelo dataset, a modelagem do grafo se encontra dentro das classes da pasta "Pajek", basicamente selecionei as músicas que tinham a maior popularidade para serem
os vértices, e para as adjacências, fiz com que cada música devia criar uma adjacência com outra desde que as médias de "danceability" e "energy" entre uma e outra seja superior a um valor, em questão disso 
foi um tempo quadrado em questão do número de vértices em relação que cada vértice teria que varrer todas as combinações.

No todo foi um dos projetos mais desafiadores que tive a oportunidade de fazer no curso e foi extremamente gratificante ter superado esse desafio.
