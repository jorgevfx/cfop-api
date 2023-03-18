# CFOP API
This API provides a list of F2L, OLL and PLL algorithms for use in the Rubik's Cube. Also known as the Fridrich Method

### API URL
`Not yet deployed`

## API Endpoints


#### Get all OLL Algorithms

```http
GET /api/v1/oll
```

#### Get a single OLL

```http
GET /api/v1/oll/${caseId}
```

`Response`
```json
  {
  "caseId": "OLL1",
  "names": [
    "Runway",
    "Blank"
  ],
  "group": "No edges flipped correctly",
  "algs": [
    {
      "moves": "(R U2) (R2' F R F' U2') (R' F R F')",
      "votes": 0
    },
    {
      "moves": "R U B' R B R2 U' R' F R F'",
      "votes": 0
    },
    {
      "moves": "(y) S U F R2 U' R2 U' R2 U R2 f",
      "votes": 0
    },
    {
      "moves": "L' U2 L2 F' L' F U2 L F' L' F",
      "votes": 0
    },
    {
      "moves": "(y) R U' R2 D' r U' r' D R2 U R'",
      "votes": 0
    }
  ]
}
```

#### Add a new algorithm to a specific OLL
```http
POST /api/v1/oll/${caseId}
```

`Body and response`
```json
  {
    "moves": "R U R' U R U2 R'"
  }
```

#### Update an existing algorithm for a specific OLL
```http
PUT /api/v1/oll/${caseId}
```

`Body and response`
```json
  {
    "oldAlg": "R U R' U R U2 R'",
    "newAlg": "R U R' U R U2 R' U"
  }
```