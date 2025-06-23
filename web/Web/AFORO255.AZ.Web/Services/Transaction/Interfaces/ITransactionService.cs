using AFORO255.AZ.Web.DTOs.Transaction;

namespace AFORO255.AZ.Web.Services.Transaction.Interfaces
{
    public interface ITransactionService
    {
        Task Post(string token, TransactionDTORequest transactionDTORequest, string type);
    }
}
