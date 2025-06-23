using AFORO255.AZ.Web.Account.Interfaces;
using AFORO255.AZ.Web.DTOs.Account;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using AFORO255.AZ.Web.Extensions;

namespace AFORO255.AZ.Web.Controllers
{
    //[Authorize]
    public class AccountController : Controller
    {
        private readonly IAccountService _accountService;

        public AccountController(IAccountService accountService)
        {
            _accountService = accountService;
        }

        public async Task<IActionResult> Index()
        {
            List<AccountDTOResponse> accountDTOResponses = await _accountService.Get(User.Identity.GetToken());
            return View(accountDTOResponses);
        }
    }
}
