using AFORO255.AZ.Web.DTOs.Transaction;
using AFORO255.AZ.Web.Proxy;
using AFORO255.AZ.Web.Services.Transaction.Interfaces;

namespace AFORO255.AZ.Web.Services.Transaction.Implementations
{
    public class TransactionService : ITransactionService
    {
        private readonly IConfiguration _configuration;
        private readonly IHttpClient _httpClient;
        public TransactionService(IConfiguration configuration, IHttpClient httpClient)
        {
            _configuration = configuration;
            _httpClient = httpClient;
        }
        public async Task Post(string token, TransactionDTORequest transactionDTORequest, string type)
        {
            string uri = $"{_configuration["Proxy:UrlGateway"]}/Transaction";
            transactionDTORequest.type = type; 
            var result = await _httpClient.PostAsync(uri, transactionDTORequest, token);
            result.EnsureSuccessStatusCode();
            if(result.StatusCode != System.Net.HttpStatusCode.Created)
            {
                throw new Exception();
            }
        }
    }
}
