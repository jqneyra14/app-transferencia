using AFORO255.AZ.Web.DTOs.Account;

namespace AFORO255.AZ.Web.Account.Interfaces
{
    public interface IAccountService
    {
        Task<List<AccountDTOResponse>> Get(string token);
    }
}
