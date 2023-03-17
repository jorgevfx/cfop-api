# CFOP API
This API provides a list of F2L, OLL and PLL algorithms for use in the Rubik's Cube. Also known as the Fridrich Method


## API Reference

### OLL

#### Get all OLL Algorithms

```http
GET /api/v1/oll
```
#### Get a single OLL

```http
GET /api/v1/oll/${caseId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `caseId`      | `string` | **Required**. Id of OLL to fetch |