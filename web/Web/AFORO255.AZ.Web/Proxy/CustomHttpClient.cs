using Newtonsoft.Json;
using System.Net;

namespace AFORO255.AZ.Web.Proxy
{
    public class CustomHttpClient : IHttpClient
    {
        private HttpClient _client;

        public CustomHttpClient()
        {
            _client = new HttpClient();
        }

        public async Task<string> GetStringAsync(string uri, string authorizationToken = null, string authorizationMethod = "Bearer")
        {

            var client = new HttpClient();
            var request = new HttpRequestMessage(HttpMethod.Get, uri);
            request.Headers.Add("accept", "*/*");
            var response = await client.SendAsync(request);
            response.EnsureSuccessStatusCode();
            var data = await response.Content.ReadAsStringAsync();






            var requestMessage = new HttpRequestMessage(HttpMethod.Get, uri);
            if (authorizationToken != null)
            {
                requestMessage.Headers.Add("Authorization", $"Bearer {authorizationToken}");
            }
            
            //var response = await _client.SendAsync(requestMessage);
            //if (response.StatusCode == HttpStatusCode.InternalServerError)
            //{
            //    throw new HttpRequestException();
            //}
            return await response.Content.ReadAsStringAsync();
        }

        public async Task<HttpResponseMessage> PostAsync<T>(string uri, T item, string authorizationToken = null, string authorizationMethod = "Bearer")
        {
            var requestMessage = new HttpRequestMessage(HttpMethod.Post, uri)
            {
                Content = new StringContent(JsonConvert.SerializeObject(item), System.Text.Encoding.UTF8, "application/json")
            };

            if (authorizationToken != null)
            {
                requestMessage.Headers.Add("Authorization", $"Bearer {authorizationToken}");
            }
            var response = await _client.SendAsync(requestMessage);

            if (response.StatusCode == HttpStatusCode.InternalServerError)
            {
                throw new HttpRequestException();
            }

            return response;
        }
    }
}
