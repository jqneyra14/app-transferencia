using AFORO255.AZ.Web.DTOs.Movement;
using AFORO255.AZ.Web.Proxy;
using MS.AFORO255.Web.Service.History.Interfaces;
using Newtonsoft.Json;
using System.Net;

namespace MS.AFORO255.Web.Service.History.Implementations
{
    public class MovementService : IMovementService
    {
        private readonly IConfiguration _configuration;
        private readonly IHttpClient _httpClient;
        public MovementService(IConfiguration configuration, IHttpClient httpClient)
        {
            _configuration = configuration;
            _httpClient = httpClient;
        }
        public async Task<TransactionPageResponseDto> GetByAccountId(string token, int accountId, DateTime? startDate, DateTime? endDate, string type, int page = 1, int size = 10)
        {
            string startDateFormateada = startDate.HasValue ? startDate.Value.ToString("dd-MM-yyyy") : string.Empty;
            string endDateFormateada = endDate.HasValue ? endDate.Value.ToString("dd-MM-yyyy") : string.Empty;

            
            var data = new TransactionPageResponseDto();
            data.Transactions = new List<TransactionDto>();
            if (string.IsNullOrEmpty(startDateFormateada))
                return data;
            if (string.IsNullOrEmpty(endDateFormateada))
                return data;
            string uri = $"{_configuration["Proxy:UrlGateway"]}/Transaction/filter?startDate=" + startDateFormateada + "&endDate=" + endDateFormateada + "&idAccount=" + accountId + "&type=" + type + "&page=" + page + "&size=" +size;
            try
            {
                var result = await _httpClient.GetStringAsync(uri, authorizationToken: token);
                //if (result.Contains("404"))
                //    return new List<MovementDTOResponse>();
                var response = JsonConvert.DeserializeObject<TransactionPageResponseDto>(result);
                return response;
            }
            catch (Exception)
            {
                return new TransactionPageResponseDto();
            }
            
        }
    }
}
