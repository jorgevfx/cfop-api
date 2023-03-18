# CFOP API
This API provides a list of F2L, OLL and PLL algorithms for use in the Rubik's Cube. Also known as the Fridrich Method

### API URL
`Not yet deployed`

## API Endpoints


#### Get all OLL Algorithms

```http
GET /api/v1/oll
```

`Response`
```json
[
  {
    "caseId": String,
    "names": Object,
    "group": String,
    "algs": Object
  }
  ...
]
```
#### Get a single OLL

```http
GET /api/v1/oll/${caseId}
```

`Response`
```json
  {
    "caseId": String,
    "names": Object,
    "group": String,
    "algs": Object
  }
```

#### Add a new algorithm to a specific OLL
```http
POST /api/v1/oll/${caseId}
```

`Body and response`
```json
  {
    "moves": String
  }
```

