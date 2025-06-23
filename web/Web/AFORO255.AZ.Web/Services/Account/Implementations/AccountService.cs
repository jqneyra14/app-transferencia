using AFORO255.AZ.Web.Account.Interfaces;
using AFORO255.AZ.Web.DTOs.Account;
using AFORO255.AZ.Web.Proxy;
using Newtonsoft.Json;

namespace AFORO255.AZ.Web.Account.Implementations
{
    public class AccountService : IAccountService
    {
        private readonly IConfiguration _configuration;
        private readonly IHttpClient _httpClient;
        public AccountService
            (IConfiguration configuration, IHttpClient httpClient)
        {
            _configuration = configuration;
            _httpClient = httpClient;
        }

        public async Task<List<AccountDTOResponse>> Get(string token)
        {
            string uri = $"{_configuration["Proxy:UrlGateway"]}/accounts";
            var result = await _httpClient.GetStringAsync(uri, authorizationToken: token);
            var response = JsonConvert.DeserializeObject<List<AccountDTOResponse>>(result);
            return response;
        }
    }
}
