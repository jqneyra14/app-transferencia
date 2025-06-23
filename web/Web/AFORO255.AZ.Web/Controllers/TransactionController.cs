using AFORO255.AZ.Web.Services.Transaction.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using AFORO255.AZ.Web.Extensions;

namespace AFORO255.AZ.Web.Controllers
{
    //[Authorize]
    public class TransactionController : Controller
    {
        private readonly ITransactionService _transactionService;

        public TransactionController(ITransactionService transactionService)
        {
            _transactionService = transactionService;
        }

        public IActionResult Index(int accountId, string type)
        {
            ViewBag.AccountId = accountId;
            ViewBag.Type = type;
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Index(int accountId, string type, decimal amount)
        {
            await _transactionService.Post(User.Identity.GetToken(),
                new DTOs.Transaction.TransactionDTORequest()
                {
                    accountId = accountId,
                    amount = amount
                }, type);
            return RedirectToAction("Index", "Account");
        }
    }
}
