###Notes

Example orderDto:
```json
{
	"Order": {
		"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
		"user": {
			"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
			"userName": "User1",
			"password": "*********",
			"firstName": "Ivan",
			"lastName": "Pupkin"
		},
		"orderItems": [{
				"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
				"product": {
					"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
					"name": "iphone",
					"manufacturer": "apple",
					"shortDescription": "a phone",
					"fullDescription": "a good phone",
					"price": 49999.99
				},
				"count": 4
			}, {
				"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
				"product": {
					"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
					"name": "galaxy s20",
					"manufacturer": "samsung",
					"shortDescription": "a phone",
					"fullDescription": "a good phone with a reasonable price",
					"price": 29999.99
				},
				"count": 6
			}
		],
		"orderStatus": {
			"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
			"status": {
				"id": "a7b72c10-a417-407f-b4c8-2b50ea591528",
				"code": "NEW",
				"description": "Order is created"
			},
			"timeChanged": "2012-04-23T18:25:43.511Z"
		}
	}
}
```

