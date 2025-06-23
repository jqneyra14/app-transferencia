using AFORO255.AZ.Web.DTOs.Security;
using AFORO255.AZ.Web.Proxy;
using MS.AFORO255.Web.Service.Auth.Interfaces;
using Newtonsoft.Json;

namespace MS.AFORO255.Web.Service.Auth.Implementations
{
    public class SecurityService : ISecurityService
    {
        private readonly IConfiguration _configuration;
        private readonly IHttpClient _httpClient;
        public SecurityService(IConfiguration configuration, IHttpClient httpClient)
        {
            _configuration = configuration;
            _httpClient = httpClient;
        }

        public async Task<SecurityDTOResponse> Login(SecurityDTORequest securityDTORequest)
        {
            string uri = $"{_configuration["Proxy:UrlGateway"]}/security/auth";
            var result = await _httpClient.PostAsync(uri, securityDTORequest);
            result.EnsureSuccessStatusCode();
            var json = await result.Content.ReadAsStringAsync();
            dynamic data = JsonConvert.DeserializeObject(json);
            return new SecurityDTOResponse() { Token = data.token };
        }
    }
}
