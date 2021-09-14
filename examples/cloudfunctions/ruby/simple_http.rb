require "functions_framework"
require "json"
require "uri"
require "net/http"

# This function receives an HTTP request of type Rack::Request
# and interprets the body as JSON. It prints the contents of
# the "message" field, or "Hello World!" if there isn't one.
FunctionsFramework.http "hello_world" do |request|
  uri = URI("http://10.128.0.7:8000")
  res = Net::HTTP.get_response(uri)
  if res.is_a?(Net::HTTPSuccess)
    res.body
  else
    "Unable to get response: " + res
  end
end
