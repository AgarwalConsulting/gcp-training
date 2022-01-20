#!/usr/bin/env python3

import json
import os
from google.auth import jwt
from google.cloud import pubsub_v1

service_account_info = json.load(open("./project-resources/tf-service-account.json"))

publisher_audience = "https://pubsub.googleapis.com/google.pubsub.v1.Publisher"

credentials_pub = jwt.Credentials.from_service_account_info(
    service_account_info, audience=publisher_audience
)

project_id = os.getenv('GOOGLE_CLOUD_PROJECT')
topic_id = "people-topic"

publisher_options = pubsub_v1.types.PublisherOptions(enable_message_ordering=True)

publisher = pubsub_v1.PublisherClient(credentials=credentials_pub, publisher_options=publisher_options)

topic_path = publisher.topic_path(project_id, topic_id)

# topic = publisher.create_topic(request={"name": topic_path})

# print(f"Created topic: {topic.name}")

for message in [
    ("Gaurav", "key1"),
    ("Shankadeep", "key2"),
    ("Rahul", "key1"),
    ("Pavan", "key2"),
]:
    # Data must be a bytestring
    data = json.dumps({'name': message[0]}).encode('utf-8')
    ordering_key = message[1]
    # When you publish a message, the client returns a future.

    future = publisher.publish(topic_path, data=data, ordering_key=ordering_key)
    print(future.result())

print(f"Published messages with ordering keys to {topic_path}.")
