using AFORO255.AZ.Web.DTOs.Movement;

namespace MS.AFORO255.Web.Service.History.Interfaces
{
    public interface IMovementService
    {
        Task<TransactionPageResponseDto> GetByAccountId(string token, int accountId, DateTime? startDate, DateTime? endDate, string type, int page = 1, int size = 10);
    }
}
