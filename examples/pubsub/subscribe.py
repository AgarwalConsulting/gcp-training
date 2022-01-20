#!/usr/bin/env python3

import os
import json
from google.auth import jwt
from google.cloud import pubsub_v1

service_account_info = json.load(open("./project-resources/tf-service-account.json"))
audience = "https://pubsub.googleapis.com/google.pubsub.v1.Subscriber"

credentials = jwt.Credentials.from_service_account_info(
    service_account_info, audience=audience
)

topic_name = 'projects/{project_id}/topics/{topic}'.format(
    project_id=os.getenv('GOOGLE_CLOUD_PROJECT'),
    topic='people-topic',  # Set this to something appropriate.
)

subscription_name = 'projects/{project_id}/subscriptions/{sub}'.format(
    project_id=os.getenv('GOOGLE_CLOUD_PROJECT'),
    sub='people-topic-sub',  # Set this to something appropriate.
)

def callback(message):
    print(message.data)
    print(message)
    message.ack()

with pubsub_v1.SubscriberClient(credentials=credentials) as subscriber:
    # subscriber.create_subscription(
    #     name=subscription_name, topic=topic_name)
    future = subscriber.subscribe(subscription_name, callback)

    try:
        future.result()
    except KeyboardInterrupt:
        future.cancel()
